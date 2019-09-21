package nl.tcilegnar.mygithub.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import nl.tcilegnar.mygithub.util.AvatarUtil

object Adapters {
    @BindingAdapter(value = ["app:fromUrl", "app:fileName"], requireAll = true)
    @JvmStatic
    fun srcFromUrl(imageView: ImageView, avatarUrl: String?, fileName: String?) {
        AvatarUtil().loadAndShowAvatar(imageView, avatarUrl, fileName)
    }
}
