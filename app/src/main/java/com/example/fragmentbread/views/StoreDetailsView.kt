package com.example.fragmentbread.views

/**
 * お店詳細画面のView インターフェイス
 */
interface StoreDetailsView : MvpView {
    /**
     * お店のお気に入り登録を切り替える
     */
    fun toggleFavoriteStore()
}