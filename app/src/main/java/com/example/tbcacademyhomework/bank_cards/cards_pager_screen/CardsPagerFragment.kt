package com.example.tbcacademyhomework.bank_cards.cards_pager_screen

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.bank_cards.BankCard
import com.example.tbcacademyhomework.bank_cards.CardsAdapter
import com.example.tbcacademyhomework.bank_cards.add_card_screen.AddCardFragment
import com.example.tbcacademyhomework.bank_cards.delete_card.DeleteCardBottomSheet
import com.example.tbcacademyhomework.bank_cards.delete_card.DeleteCardBottomSheet.Companion.REMOVE_CARD_KEY
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentCardsPagerBinding


class CardsPagerFragment :
    BaseFragment<FragmentCardsPagerBinding>(FragmentCardsPagerBinding::inflate) {


    private val viewModel: CardsPagerViewModel by viewModels()

    private val viewPagerAdapter by lazy {
        CardsAdapter(::openBottomSheet)
    }
    private lateinit var navController: NavController

    override fun init(savedInstanceState: Bundle?) {
        navController = findNavController()
        initListeners()
        setupPager()

    }


    private fun setupPager() {

        binding.vpCards.apply {
            adapter = viewPagerAdapter
            offscreenPageLimit = 1

            setPageTransformer { page, position ->
                val scaleFactor = 0.85f
                val scale = scaleFactor.coerceAtLeast(1 - kotlin.math.abs(position) * 0.1f)
                page.scaleY = scale
            }

            addItemDecoration(object : ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
                ) {
                    outRect.right = resources.getDimensionPixelOffset(R.dimen.size16)
                }
            })

        }


        viewPagerAdapter.submitList(viewModel.getCards())


    }

    private fun initListeners() {
        setFragmentResultListener(REQUEST_KEY) { _, bundle ->
            getCard(bundle)
        }

        setFragmentResultListener(REMOVE_CARD_REQUEST_KEY) { _, bundle ->
            removeCard(bundle)
        }

        binding.btnAddCard.setOnClickListener {
            navController.navigate(CardsPagerFragmentDirections.actionCardsPagerFragmentToAddCardFragment())
        }

        viewPagerAdapter.onDataCommitListener {
            binding.vpCards.apply {
                post {
                    currentItem = 0
                }
            }

        }
    }

    private fun removeCard(bundle: Bundle) {
        val cardId = bundle.getString(REMOVE_CARD_KEY)
        cardId?.let {
            viewModel.removeCard(it)
            viewPagerAdapter.submitList(viewModel.getCards())
        }
    }

    @Suppress("DEPRECATION")
    private fun getCard(bundle: Bundle) {
        val card = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(CARD_KEY, BankCard::class.java)
        } else {
            bundle.getParcelable(CARD_KEY) as? BankCard
        }

        card?.let {
            viewModel.addCard(it)
            viewPagerAdapter.submitList(viewModel.getCards())

        }
    }

    private fun openBottomSheet(cardId: String) {
        navController.navigate(
            CardsPagerFragmentDirections.actionCardsPagerFragmentToDeleteCardBottomSheet2(
                cardId
            )
        )
    }

    companion object {
        const val REQUEST_KEY = "request_key"
        const val REMOVE_CARD_REQUEST_KEY = "remove_request_key"
        const val CARD_KEY = "bank_card"


    }


}