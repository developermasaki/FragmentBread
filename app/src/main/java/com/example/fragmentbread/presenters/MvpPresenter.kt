package com.example.fragmentbread.presenters

/**
 * MVP用のPresenterを定義する
 *
 *
 */
interface MvpPresenter<V> {
    /**
     * presenterにviewを登録する
     */
    fun onAttachView(view: V)

    /**
     * presenterからviewを解除する
     */
    fun onDetachView()
}