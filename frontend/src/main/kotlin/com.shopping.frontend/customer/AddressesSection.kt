package com.shopping.frontend.customer

import com.shopping.common.DummyData
import com.shopping.common.domain.model.valueObject.Address
import com.shopping.frontend.fc
import react.RProps
import styled.styledH3

val AddressesSection = fc<RProps> {

    val addresses = DummyData.customer.addresses

    addresses.forEach { address ->
        AddressItem {
            this.address = address
        }
    }

}

private external interface AddressItemProps : RProps {
    var address: Address
}

private val AddressItem = fc<AddressItemProps> { props ->

    styledH3 {
        +props.address.name
    }


}