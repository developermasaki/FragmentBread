package com.example.fragmentbread.views

/**
 * お店詳細画面のView インターフェイス
 */
interface StoreDetailsView : MvpView {
    /**
     * お店をお気に入り登録する
     */
    fun addFavoriteStore()
}