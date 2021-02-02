package com.shopping.frontend

import kotlinx.css.*
import kotlinx.css.properties.*
import styled.StyleSheet

private object Shapes {
    val small = 8.px
    val medium = 16.px
    val large = 24.px
}


object Styles : StyleSheet("HomeStyles") {

    object Customer {

        val Root by css {
            +Components.Row
        }

        val Main by css {
            +Components.Column
        }

        val Image by css {
            borderRadius = 12.px
            width = 50.pct
            height = 50.pct
            maxWidth = 50.pct
            maxHeight = 50.pct
            backgroundColor = Colors.primary
        }

        val Name by css {

        }

        val Email by css {

        }

        object Aside {

            val Root by css {
                +Components.Column
            }

            val AsideItem by css {
                
            }

        }

    }


    val Root by css {
    }

    object NavBar {
        val Root by css {
            width = 100.vw
            display = Display.flex
            justifyContent = JustifyContent.spaceAround
            alignItems = Align.center
            background = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSj3XfIpf-OOJRCcWz4iN2CU3qMKVVbj0p0bRvKNGVo1U9pk_8ZIlyR8pWreA"
        }

        val Title by css {
            fontWeight = FontWeight.w600
            marginRight = LinearDimension.auto
            margin(horizontal = 2.rem)
        }

        val Search by css {
            borderRadius = LinearDimension.none
            padding(0.5.rem)
            margin(horizontal = LinearDimension.auto)
            outline = Outline.none
            boxShadow = BoxShadows.none
        }

        val Profile by css {
            objectFit = ObjectFit.contain
            maxWidth = 100.pct
            maxHeight = 100.pct
            width = 50.px
            height = 50.px
            borderRadius = 24.px
            margin(horizontal = 2.rem)
        }

        val Item by css {
            margin(horizontal = 1.rem)
            textDecoration = TextDecoration.none
            hover {
                textDecoration(TextDecorationLine.underline)
            }
        }
    }

    object Products {

        val Root by css {
            display = Display.flex
            flexWrap = FlexWrap.wrap
            alignItems = Align.center
            justifyContent = JustifyContent.center
        }

        val Categories by css {
            display = Display.flex
            justifyContent = JustifyContent.spaceEvenly
            alignItems = Align.center
        }

        val Category by css {

        }

        object Product {

            val Root by css {
                display = Display.flex
                boxSizing = BoxSizing.borderBox
                flexDirection = FlexDirection.column
                overflow = Overflow.hidden
                maxHeight = 15.pct
                maxWidth = 15.pct
                borderRadius = Shapes.medium
                textDecoration = TextDecoration.none
                color = Color.black
                boxShadow(Color.black.withAlpha(0.05), Shapes.small, Shapes.small, Shapes.small)
                margin(2.4.rem)
                hover {
                    transition(duration = 350.ms)
                    transform {
                        scale(1.05)
                    }
                }
            }

            val Image by css {
                objectFit = ObjectFit.contain
                maxHeight = 100.pct
                maxWidth = 100.pct
            }

            val Brand by css {
                padding(horizontal = 1.rem)
                color = Color.gray
                fontWeight = FontWeight.w600
                fontSize = 2.rem
            }

            val Name by css {
                padding(horizontal = 1.rem)
                maxWidth = 100.pct
                overflow = Overflow.hidden
                whiteSpace = WhiteSpace.nowrap
                textOverflow = TextOverflow.ellipsis
            }

            val Price by css {
                padding(horizontal = 1.rem)
            }


        }

    }


    object Components {

        val Row by css {
            display = Display.flex
            flexDirection = FlexDirection.row
            alignItems = Align.center
            justifyContent = JustifyContent.center
        }

        val Column by css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.center
            justifyContent = JustifyContent.center
        }

        val TextField by css {
            backgroundColor = Colors.surface
            color = Colors.onSurface
            outline = Outline.none
            fontWeight = FontWeight.w600
            fontSize = 1.5.rem
            borderColor = Color.transparent
            borderRadius = Shapes.small
            padding(1.rem)
            focus {
                transition(duration = 150.ms)
                backgroundColor = Colors.background
                boxShadow(Color.black.withAlpha(0.25), blurRadius = Shapes.small)
            }
        }

        val PrimaryButton by css {
            backgroundColor = Colors.primary
            color = Colors.onPrimary
            fontSize = 2.rem
            fontWeight = FontWeight.w500
            cursor = Cursor.pointer
            borderColor = Color.transparent
            borderRadius = Shapes.small
            outline = Outline.none
            padding(1.rem)
            boxShadow(Colors.primary, blurRadius = Shapes.small)
            hover {
                transition(duration = 150.ms)
                backgroundColor = Colors.primary.darken(25)
            }
            active {
                transition(duration = 150.ms)
                boxShadow(Colors.primary, 4.px, 4.px, blurRadius = Shapes.small)
            }
        }
    }


    object SignScreen {

        val Root by css {
            width = 100.pct
            height = 100.pct
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = Align.center
        }


        val SignForm by css {
            width = 50.pct
            height = 75.pct
            display = Display.flex
            flexDirection = FlexDirection.column
            justifyContent = JustifyContent.center
            alignItems = Align.center
            borderRadius = Shapes.small
            backgroundColor = Colors.background
            color = Colors.onBackground
            overflow = Overflow.hidden
            boxShadow(Color.black.withAlpha(0.1), blurRadius = Shapes.small)
        }

        val Input by css {
            width = 50.pct
            margin(1.rem)
        }

        val Button by css {
            width = 25.pct
            margin(1.rem)
        }

        val AlternativeSign by css {

        }

    }
}