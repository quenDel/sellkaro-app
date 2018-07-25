package com.sellkaro.utils

import dmax.dialog.SpotsDialog

class AppProgressDialog {

    companion object {

        fun show(dialog: SpotsDialog?) {
            hide(dialog)
            dialog?.show()
        }

        fun hide(dialog: SpotsDialog?) {
            if (dialog != null) {
                if (dialog.isShowing) {
                    dialog.dismiss()
                }
            }
        }
    }
} 