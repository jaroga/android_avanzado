package com.danielceinos.imgram.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.danielceinos.imgram.R
import com.danielceinos.imgram.databinding.HomeActivityBinding
import com.danielceinos.imgram.databinding.SharePopupLayoutBinding
import com.danielceinos.imgram.domain.image.Image
import com.danielceinos.imgram.domain.image.Image.Tag
import com.danielceinos.imgram.ui.home.UploadViewData.Status.*
import com.esafirm.imagepicker.features.ImagePicker
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.direct
import org.kodein.di.instance
import timber.log.Timber
import com.esafirm.imagepicker.model.Image as PickerImage


@ExperimentalCoroutinesApi
class HomeActivity : AppCompatActivity(), DIAware {

    override val di: DI by di()

    private lateinit var binding: HomeActivityBinding
    private lateinit var adapter: GalleryRecyclerAdapter
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, direct.instance()).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        configureRecyclerView()
        observeViewModel()
        configureBottomNav()

        viewModel.processIntentData(intent.data)
    }

    private fun configureBottomNav() {
        binding.homeBottomNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_hot -> viewModel.loadHotGallery()
                R.id.menu_top -> viewModel.loadTopGallery()
                R.id.menu_me -> viewModel.loadMyGallery()
                R.id.menu_account -> goToOauth2()
            }
            true
        }
        binding.homeBottomNavBar.selectedItemId = R.id.menu_top

        binding.homeAddButton.setOnClickListener {
            openGallery()
        }
    }

    private fun configureRecyclerView() {
        binding = HomeActivityBinding.inflate(layoutInflater).also { setContentView(it.root) }
        adapter = GalleryRecyclerAdapter(
            onDelete = {
                viewModel.deleteImage(it)
            },
            onShare = {
                showSharePopup(it)
            }
        ).also { binding.galleryRecyclerView.adapter = it }
    }

    private fun showSharePopup(image: Image) {

        val builder = MaterialAlertDialogBuilder(this)
        val dialogBinding = SharePopupLayoutBinding.inflate(layoutInflater, null, false)
        builder.setView(dialogBinding.root)
        builder.setPositiveButton("OK") { _, _ ->
            val title = dialogBinding.titleEditText.text.toString()

            val chipIds = dialogBinding.chipgroup.children
            val tagList = chipIds.filter { it is Chip }.map { chip ->
                Tag((chip as Chip).text.toString())
            }.toList()
            viewModel.shareImage(image, title, tagList)
        }
        builder.setNegativeButton("Cancel") { _, _ ->

        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

        dialogBinding.addTagButton.setOnClickListener {
            val chip = Chip(binding.root.context)
            chip.text = dialogBinding.tagEditText.text.toString()
            chip.setCloseIconResource(R.drawable.ic_cancel)
            chip.isCloseIconVisible = true
            chip.setOnCloseIconClickListener {
                dialogBinding.chipgroup.removeView(chip)
            }
            dialogBinding.chipgroup.addView(chip)
            dialogBinding.tagEditText.setText("")
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.homeVD.collect {
                adapter.images = it.images
                binding.homeProgressBar.visibility = when (it.loading) {
                    true -> View.VISIBLE
                    false -> View.GONE
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.sessionVD.collect {
                binding.homeBottomNavBar.menu.findItem(R.id.menu_account).apply {
                    title = when (it.hasSession) {
                        true -> it.accountName!!
                        false -> "Signin"
                    }
                    isEnabled = !it.hasSession
                }
                binding.homeBottomNavBar.menu.findItem(R.id.menu_me).isVisible = it.hasSession
                binding.homeAddButton.isVisible = it.hasSession
                adapter.loggedAuthor = it.accountName
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.uploadViewData.collect {
                when (it?.status) {
                    FINISHED -> showSnackBar(
                        text = "Upload finished!",
                        duration = Snackbar.LENGTH_LONG,
                        actionLabel = "Go"
                    ) { binding.homeBottomNavBar.selectedItemId = R.id.menu_me }
                    UPLOADING ->
                        showSnackBar(
                            text = "Uploading images ${it.uploaded}/${it.totalImages}",
                            duration = Snackbar.LENGTH_SHORT,
                            actionLabel = "Cancel"
                        ) { viewModel.cancelUpload() }
                    CANCELLED ->
                        showSnackBar(
                            text = "Upload canceled ${it.uploaded}/${it.totalImages}",
                            duration = Snackbar.LENGTH_SHORT
                        )
                }
            }
        }
    }


    private fun showSnackBar(text: CharSequence, duration: Int, actionLabel: CharSequence? = null, action: (View) -> Unit = {}) {
        Snackbar.make(binding.homeCoordinator, text, duration)
            .apply { actionLabel?.let { setAction(actionLabel, action) } }
            .show()
    }

    private fun goToOauth2() {
        val url = "https://api.imgur.com/oauth2/authorize?client_id=3795c60af5383c1&response_type=token"
        Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) }.also { startActivity(it) }
    }

    private fun openGallery() {
        ImagePicker.create(this).start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val images: List<PickerImage> = ImagePicker.getImages(data)
            viewModel.uploadFile(images.map { it.path })
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}