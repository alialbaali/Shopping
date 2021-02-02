package com.shopping.frontend.customer

import com.shopping.common.DummyData
import com.shopping.common.domain.model.valueObject.Card
import com.shopping.frontend.fc
import react.RProps
import styled.styledH3

val CardsSection = fc<RProps> {

    val cards = DummyData.customer.cards

    cards.forEach { card ->
        CardItem {
            this.card = card
        }
    }
}

private external interface CardItemProps : RProps {
    var card: Card
}

private val CardItem = fc<CardItemProps> { props ->
    styledH3 {
        +props.card.brand
    }
}