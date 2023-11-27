package com.allacsta.composesubmission.ui.screen.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.allacsta.composesubmission.R
import com.allacsta.composesubmission.di.Injection
import com.allacsta.composesubmission.model.OrderCoffee
import com.allacsta.composesubmission.ui.ViewModelFactory
import com.allacsta.composesubmission.ui.common.UiState
import com.allacsta.composesubmission.ui.components.CoffeeItem
import com.allacsta.composesubmission.ui.components.Search


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val query by viewModel.query
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center,
    ) {
        Search(
            query = query,
            onQueryChange = viewModel::search,
            modifier = Modifier.background(MaterialTheme.colorScheme.primary)
        )
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllCoffees()
                }
                is UiState.Success -> {
                    if (uiState.data.isEmpty()){
                        Toast.makeText(
                            context,
                            context.resources.getString(R.string.data_empty),
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        HomeContent(
                            orderCoffee = uiState.data,
                            modifier = modifier,
                            navigateToDetail = navigateToDetail,
                        )
                    }
                }
                is UiState.Error -> {}
            }
        }
    }

}

@Composable
fun HomeContent(
    orderCoffee: List<OrderCoffee>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(140.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("CoffeeList")
    ) {
        items(orderCoffee) { data ->
            CoffeeItem(
                image = data.coffee.image,
                title = data.coffee.title,
                requiredPoint = data.coffee.requiredPoint,
                modifier = Modifier.clickable {
                    navigateToDetail(data.coffee.id)
                }
            )
        }
    }
}