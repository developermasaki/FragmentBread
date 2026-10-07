package com.example.fragmentbread.presenters

/**
 * MVP用のPresenterを定義する
 */
interface MvpPresenter {
    /**
     * presenterにviewを登録する
     */
    fun onAttachView()

    /**
     * presenterからviewを解除する
     */
    fun onDetachView()
}