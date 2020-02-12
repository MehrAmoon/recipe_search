package com.amoon.recipelist.ui


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amoon.recipelist.BaseActivity
import com.amoon.recipelist.R
import com.amoon.recipelist.data.model.Drink
import com.amoon.recipelist.databinding.ActivitySearchBinding
import com.amoon.recipelist.ui.detail.DetailFragment
import com.amoon.recipelist.ui.main.MainAdapter
import kotlinx.android.synthetic.main.activity_search.*


annotation class MyFrameworkAnnotation

@MyFrameworkAnnotation
class MainActivity : BaseActivity<ActivitySearchBinding, MainViewModel>(), MainAdapter.OnClickListener {

    private lateinit var recipeAdapter: MainAdapter
    override fun getModelClass(): Class<MainViewModel> = MainViewModel::class.java
    override fun getLayoutRes(): Int = R.layout.activity_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchKeyWord()
        recipeAdapter = MainAdapter(this )

        viewModel.run {
            pages.observe(this@MainActivity, Observer {
                recipeAdapter.submitList(it)
                binding.isLoading = true
            })
            networkState.observe(this@MainActivity, Observer {
                binding.errorText.text =  "No item found"
                binding.isFailed = true
            })

        }

        setupRecycler()
        viewModel.listRecipe("Gin")
    }

    private fun setupRecycler() {
        with(imageRecycler) {
            layoutManager = GridLayoutManager(this@MainActivity,2) as RecyclerView.LayoutManager?
            adapter = recipeAdapter
        }
        return
    }


    // get the keywords from earchview and searched based on it
    private fun searchKeyWord() {

        ingSearchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.isLoading = false
                binding.isFailed = false
                viewModel.listRecipe(query)
                recipeAdapter?.updateData()
                return false
            }
        })
    }


    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        when {
            count == 0 -> {
                super.onBackPressed()
            }
            else -> supportFragmentManager.popBackStack()
        }
    }


    override fun onItemClick(drink: Drink) {
        supportFragmentManager
            .beginTransaction().setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .add(
                R.id.mainActivityRoot, DetailFragment.newInstance(
                    drink.idDrink,
                    drink.strDrink,
                    drink.strDrinkThumb
                ), "RecipeList"
            )
            .addToBackStack(DetailFragment::class.java.name).commit()
    }

}
