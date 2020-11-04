package com.machmudow.shoppinglists.infrastructure.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.machmudow.shoppinglists.getOrAwaitValue
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingListDAOTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: ShoppingListDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getShoppingListDAO()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertShoppingList() {
        val shoppingList = ShoppingList(title = "Test")
        dao.insertShoppingList(shoppingList)

        val shoppingListWithItems = dao.getShoppingListsWithItems().getOrAwaitValue()

        assertThat(shoppingListWithItems.map { it.shoppingList }.contains(shoppingList))
    }

    @Test
    fun delete() = runBlockingTest {
        val shoppingList = ShoppingList(title = "Test")
        dao.insertShoppingList(shoppingList)
        dao.delete(shoppingList.id)

        val shoppingListWithItems = dao.getShoppingListsWithItems().getOrAwaitValue()

        assertThat(!shoppingListWithItems.map { it.shoppingList }.contains(shoppingList))
    }

    @Test
    fun archive() = runBlockingTest {
        val shoppingList = ShoppingList(title = "Test")
        dao.insertShoppingList(shoppingList)

        dao.archive(shoppingList.id)

        val shoppingListWithItems = dao.getShoppingListsWithItems().getOrAwaitValue()

        assertThat(!shoppingListWithItems.map { it.shoppingList }.contains(shoppingList))
    }

    @Test
    fun unarchive() = runBlockingTest{
        val shoppingList = ShoppingList(title = "Test", isArchived = true)
        dao.insertShoppingList(shoppingList)

        dao.unarchive(shoppingList.id)

        val shoppingListWithItems = dao.getShoppingListsWithItems().getOrAwaitValue()

        assertThat(!shoppingListWithItems.map { it.shoppingList }.contains(shoppingList))
    }
}