package com.testapp.fetchrewards.models


sealed class RecyclerViewSection(val rowType: RowType) {
    data class Header(val name: String) :RecyclerViewSection(RowType.Header)
    data class ItemRV(
        val id: Int,
        val name: String
    ):RecyclerViewSection(RowType.Details)
}
enum class RowType {
    Details,
    Header
}
