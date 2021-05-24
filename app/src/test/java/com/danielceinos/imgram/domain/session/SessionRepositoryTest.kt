package com.danielceinos.imgram.domain.session

import com.danielceinos.imgram.data.session.SessionLocalDataSource
import com.danielceinos.imgram.data.session.SessionMemoryDataSource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class SessionRepositoryTest {

    private val session = Session(
        "token",
        10,
        "type",
        "refresh",
        "username",
        "accountId"
    )

    @Test
    fun `retrieve no session ok`() {
        val local = mock<SessionLocalDataSource> {
            on { getSession() }.thenReturn(null)
        }
        val memory = mock<SessionMemoryDataSource> {
            on { getSession() }.thenReturn(null)
        }

        val repository = SessionRepository(local, memory)

        assertNull(repository.getSession())
    }

    @Test
    fun `retrieve session from local`() {
        val local = mock<SessionLocalDataSource> {
            on { getSession() }.thenReturn(session)
        }
        val memory = mock<SessionMemoryDataSource> {
            on { getSession() }.thenReturn(null)
        }

        val repository = SessionRepository(local, memory)

        assertEquals(session, repository.getSession())
        verify(memory).saveSession(session)
    }

    @Test
    fun `retrieve session from memory`() {
        val local = mock<SessionLocalDataSource> {
            on { getSession() }.thenReturn(null)
        }
        val remote = mock<SessionMemoryDataSource> {
            on { getSession() }.thenReturn(session)
        }

        val repository = SessionRepository(local, remote)

        assertEquals(session, repository.getSession())
    }

}