package com.allacsta.composesubmission.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.allacsta.composesubmission.R
import com.allacsta.composesubmission.model.Coffee
import com.allacsta.composesubmission.model.OrderCoffee
import com.allacsta.composesubmission.onNodeWithStringId
import com.allacsta.composesubmission.ui.theme.CoffeeShopTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenKtTest{
    @get:Rule

    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val fakeOrderCoffee = OrderCoffee(
        coffee = Coffee(4, R.drawable.flat_white_hazelnut, "Flat White Hazelnut", 22000, "The flat white is the new kid on the block when it comes to coffee. Originating from Australia or New Zealand, depending on who you talk to, it is now filling menus in coffee shops around the world"),
        count = 0
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CoffeeShopTheme {
                DetailContent(
                    fakeOrderCoffee.coffee.image,
                    fakeOrderCoffee.coffee.title,
                    fakeOrderCoffee.coffee.requiredPoint,
                    fakeOrderCoffee.count,
                    fakeOrderCoffee.coffee.description,
                    onBackClick = {},
                    onAddToCart = {}
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakeOrderCoffee.coffee.title).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.required_point,
                fakeOrderCoffee.coffee.requiredPoint
            )
        ).assertIsDisplayed()
    }

    @Test
    fun increaseProduct_buttonEnabled() {
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsNotEnabled()
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsEnabled()
    }

    @Test
    fun increaseProduct_correctCounter() {
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick().performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("2"))
    }

    @Test
    fun decreaseProduct_stillZero() {
        composeTestRule.onNodeWithStringId(R.string.minus_symbol).performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("0"))
    }
}