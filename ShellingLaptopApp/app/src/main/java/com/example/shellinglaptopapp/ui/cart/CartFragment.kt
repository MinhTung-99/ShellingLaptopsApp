package com.example.shellinglaptopapp.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.data.model.Cart
import com.example.shellinglaptopapp.ui.MainActivity
import com.example.shellinglaptopapp.ui.laptops.detail.DetailLaptopsFragment
import com.example.shellinglaptopapp.ui.order.OrderFragment
import com.example.shellinglaptopapp.ui.share.ShareCartViewModel
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment: Fragment(), RecyclerViewCartClickListener {

    private val cartViewModel by lazy {
        ViewModelProvider(this).get(CartViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewModel.carts.observe(viewLifecycleOwner, {
            if(it.isEmpty()){
                txt_cart.visibility = View.VISIBLE
                rv_product.visibility = View.GONE
                txt_cart.text = "Giỏ hàng trống"
            }else {
                txt_cart.visibility = View.GONE
                rv_product.visibility = View.VISIBLE

                rv_product.adapter = CartAdapter(it, this)

//                val itemDeclaration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
//                var horizontalDivider = ContextCompat.getDrawable(activity!!, R.drawable.line_divider)
//                itemDeclaration.setDrawable(horizontalDivider!!)
                //rv_product.addItemDecoration(itemDeclaration)
            }
        })
    }

    override fun orderOnClick(cart: Cart) {
        val viewModel = ViewModelProvider(requireActivity()).get(ShareCartViewModel::class.java)
        viewModel.cart.value = cart

//        val fragmentTransaction = activity!!.supportFragmentManager?.beginTransaction()
//        val orderFragment = OrderFragment()
//        fragmentTransaction.replace(R.id.fragment, orderFragment)
//        fragmentTransaction.addToBackStack(null)
//        fragmentTransaction.commit()
    }

    override fun deleteOnClick(cart: Cart) {
        cartViewModel.deleteCart(cart)
        Toast.makeText(context, "deleted",Toast.LENGTH_SHORT).show()
    }
}