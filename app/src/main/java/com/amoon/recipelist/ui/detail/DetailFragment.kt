package com.amoon.recipelist.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.amoon.recipelist.R
import com.amoon.recipelist.databinding.GitDetailBinding
import com.amoon.recipelist.util.WEB_URL
import kotlinx.android.synthetic.main.git_detail.*
import javax.inject.Inject


class DetailFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: GitDetailBinding


    companion object {

        private const val Name = "Name"
        private const val ID = "ID"
        private const val Photo = "Photo"

        //new instance for showing recipe details
        fun newInstance(id: String, name: String, photo: String?) = DetailFragment().apply {
            arguments = Bundle(3).apply {

                putString(Name, name)
                putString(ID, id)
                putString(Photo, photo)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.git_detail, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recipeDetails()
    }


    fun recipeDetails() {
//        observe data from livedata and show on ui
        if (arguments?.getString(Photo) != null)
            Glide.with(this).load(arguments?.getString(Photo)).into(image)

        recipeName.text = arguments?.getString(Name)


        recipeButton.setOnClickListener {
            recipeWebView.visibility = View.VISIBLE
            recipeWebView.loadUrl(WEB_URL)

            // Enable Javascript and force links and redirects to open in the WebView instead of in a browser
            val webSettings: WebSettings = recipeWebView.getSettings()
            webSettings.javaScriptEnabled = true
            recipeWebView.webViewClient = WebViewClient()
        }



    }

}