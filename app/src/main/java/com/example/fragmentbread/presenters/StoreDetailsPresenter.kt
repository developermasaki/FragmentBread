package com.example.fragmentbread.presenters

import com.example.fragmentbread.views.StoreDetailsView

class StoreDetailsPresenter : MvpPresenter<StoreDetailsView> {
    private var _view: StoreDetailsView? = null
    override fun onAttachView(view: StoreDetailsView) {
        _view = view
    }

    override fun onDetachView() {
        _view = null
    }
}