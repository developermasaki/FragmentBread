package com.example.fragmentbread.presenters

import com.example.fragmentbread.views.StoreDetailsView

class StoreDetailsPresenter : MvpPresenter<StoreDetailsView> {
    private var _view: StoreDetailsView? = null
    override fun onAttachView(view: StoreDetailsView) {
        _view = view
    }

    fun toggleFavoriteStore() {
        // TODO お店のお気に入りの切り替えをrepository経由で、roomのデータを切り替える
        _view?.toggleFavoriteStore()
    }

    override fun onDetachView() {
        _view = null
    }
}