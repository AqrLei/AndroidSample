package com.aqrlei.sample.widget.banner

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.aqrlei.helper.log.LogHelper
import com.aqrlei.sample.widget.R
import com.aqrlei.sample.base.BaseFragment
import com.aqrlei.bannerview.widget.BannerView
import com.aqrlei.bannerview.widget.indicator.FigureIndicatorView
import com.aqrlei.bannerview.widget.banner2.BannerView2
import com.aqrlei.util.DensityUtil
import com.aqrlei.util.toast.ToastHelper
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.drm.DrmSessionManager
import com.google.android.exoplayer2.drm.ExoMediaCrypto
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.view_banner_item.view.*
import kotlinx.android.synthetic.main.view_frag_banner_test.*
import kotlin.random.Random

/**
 * created by AqrLei on 2019-12-13
 */
class BannerTestFragment : BaseFragment() {
    private val colorList: MutableList<Int> by lazy {
        ArrayList<Int>().also {
            it.add(Color.BLACK)
            it.add(Color.DKGRAY)
            it.add(Color.GRAY)
            it.add(Color.LTGRAY)
            it.add(Color.WHITE)
            it.add(Color.RED)
            it.add(Color.GREEN)
            it.add(Color.BLUE)
            it.add(Color.YELLOW)
            it.add(Color.CYAN)
            it.add(Color.MAGENTA)
        }

    }
    private val thumbUrl = "https://mktv-in-cdn.mockuai.com/15889314436088767.jpg"
    private val videoUrl = "https://mktv-in-cdn.mockuai.com/15889315194759191.mp4"
    private val list: MutableList<BannerBean> = ArrayList()

    private val bannerAdapter = BannerView.Adapter(SimpleBannerViewHolder()).also { adapter ->
        adapter.pageChangeListener = object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                LogHelper.d("LogHelper", "LogHelper: onPageSelected: position - $position")
            }
        }
        adapter.setPageClickListener(object : BannerView.Adapter.OnPageClickListener {
            override fun onPageClick(v: View, position: Int) {
                ToastHelper.shortShow("Click BannerPage: $position")
            }
        })
    }

    private val bannerAdapter2 = BannerView2.Adapter(SimpleBannerViewHolder2()).also { adapter ->
        adapter.pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                LogHelper.d("AqrLei", "onPageSelected2: position - $position")
                if (adapter.viewHolder.getItemViewType(position) != 2) {
                    adapter.viewHolder.playerControlView?.player = null
                } else {
                    adapter.viewHolder.playerControlView?.player =
                        adapter.viewHolder.exoPlayer?.also {
                        }
                }
            }
        }
        adapter.pageClickCallback = object : BannerView2.Adapter.OnPageClickCallback() {
            override fun onClick(view: View, position: Int) {
                ToastHelper.shortShow("Click BannerPage2 : $position")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_frag_banner_test, container, false)
    }

    private val index
        get() = Random.nextInt(0, list.size)

    private var i = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager(view)
        list.addAll(getBannerContentList())
        bannerAdapter.notifyDataChange()
        bannerAdapter2.notifyDataChange()
        tvClickLayout.setOnClickListener {
            (bannerView2.indicatorView as? View)?.requestLayout()
        }

        tvClickSet.setOnClickListener {
            bannerViewWorm.setCurrentItem(index.also {
                ToastHelper.shortShow("Click to set at Position : $it")
            })
            bannerView2.setCurrentItem(index.also {
                ToastHelper.shortShow("Click to set at Position2 : $it")
            })
        }
        tvClickAdd.setOnClickListener {
            list.add(BannerBean(Color.RED, "2"))
            bannerAdapter.notifyDataChange()
            bannerAdapter2.notifyDataChange()
        }
        tvClickReplace.setOnClickListener {
            i++
            list.clear()
            list.addAll(getBannerContentList(i % 2 == 0))
            bannerAdapter.notifyDataChange()
            bannerAdapter2.notifyDataChange()
        }
    }

    private fun getBannerContentList(reverse: Boolean = false) = ArrayList<BannerBean>().also {
        for (i in 0 until colorList.size) {
            if (i == 0) {
                it.add(BannerBean(colorList[i], "", videoUrl, thumbUrl))
            } else {
                it.add(BannerBean(colorList[i], ""))
            }
        }
        if (reverse) {
            it.reverse()
        }
    }

    private fun setupViewPager(view: View) {
        view.findViewById<BannerView>(R.id.bannerViewWorm).apply {
        }.adapter = bannerAdapter

        view.findViewById<BannerView2>(R.id.bannerView2).apply {
            indicatorView = FigureIndicatorView(this@BannerTestFragment.context!!).also {
                val dp6 = DensityUtil.dp2px(6F)
                it.setPadding(dp6, dp6 / 2, dp6, dp6 / 2)
            }
            adapter = bannerAdapter2
        }
    }

    inner class SimpleBannerViewHolder : BannerView.ViewHolder() {
        override fun getLayoutId(viewType: Int): Int = R.layout.view_banner_item

        override fun getItemCount(): Int = list.size

        override fun onViewCreate(view: View, parent: ViewGroup, position: Int): View {
            return view.apply {
                view.tvBanner.setBackgroundColor(list[position].color)
                view.tvBanner.text = "$position"
            }
        }
    }

    inner class SimpleBannerViewHolder2 : BannerView2.ViewHolder() {

        var exoPlayer: SimpleExoPlayer? = null

        var surfaceControl: SurfaceControl? = null

        var videoSurface: Surface? = null


        var playerControlView: PlayerControlView? = null
            private set

        private fun initializePlayer() {
            if (exoPlayer != null && surfaceControl != null && videoSurface != null) return
            val uri = Uri.parse(videoUrl)
            val drmSessionManager: DrmSessionManager<ExoMediaCrypto> =
                DrmSessionManager.getDummyDrmSessionManager()
            val dataSourceFactory: DataSource.Factory =
                DefaultDataSourceFactory(
                    this@BannerTestFragment.context!!,
                    Util.getUserAgent(
                        this@BannerTestFragment.context!!, "AndroidSample"
                    )
                )
            val mediaSource: MediaSource
            @C.ContentType val type = Util.inferContentType(uri, "")
            mediaSource = when (type) {
                C.TYPE_DASH -> {
                    DashMediaSource.Factory(dataSourceFactory)
                        .setDrmSessionManager(drmSessionManager)
                        .createMediaSource(uri)
                }
                C.TYPE_OTHER -> {
                    ProgressiveMediaSource.Factory(dataSourceFactory)
                        .setDrmSessionManager(drmSessionManager)
                        .createMediaSource(uri)
                }
                else -> {
                    throw IllegalStateException()
                }
            }
            val player: SimpleExoPlayer =
                SimpleExoPlayer.Builder(this@BannerTestFragment.context!!.applicationContext)
                    .build()
            player.prepare(mediaSource)
            player.playWhenReady = false
            player.repeatMode = Player.REPEAT_MODE_ALL



            surfaceControl =
                SurfaceControl.Builder()
                    .setName("exoplayerSurface")
                    .setBufferSize( /* width= */0,  /* height= */0)
                    .build()
            videoSurface = Surface(surfaceControl!!)
            player.setVideoSurface(videoSurface)
            LogHelper.d(
                "ExoPlayer",
                "width: ${player.videoFormat?.width} , height : ${player.videoFormat?.height}"
            )
            exoPlayer = player
        }

        private fun setSurface(surfaceView: SurfaceView) {
            surfaceControl?.let {
                val newParentSurfaceControl = surfaceView.surfaceControl
                SurfaceControl.Transaction()
                    .reparent(it, newParentSurfaceControl)
                    .setBufferSize(it, surfaceView.width, surfaceView.height)
                    .setVisibility(it, true)
                    .apply()
            }
        }

        private fun attachSurfaceListener(surfaceView: SurfaceView) {
            surfaceView.holder
                .addCallback(object : SurfaceHolder.Callback {
                    override fun surfaceCreated(holder: SurfaceHolder?) {
                        setSurface(surfaceView)
                    }

                    override fun surfaceChanged(
                        holder: SurfaceHolder?,
                        format: Int,
                        width: Int,
                        height: Int
                    ) {
                    }

                    override fun surfaceDestroyed(holder: SurfaceHolder?) {

                    }
                })
        }

        override fun getLayoutId(viewType: Int): Int = when (viewType) {
            2 -> R.layout.view_banner_video_item
            else -> R.layout.view_banner_item
        }

        override fun getItemCount(): Int = list.size

        override fun getItemViewType(position: Int): Int? {
            return if (list[position].videoUrl.isNotEmpty()) 2 else null
        }

        override fun bindView(position: Int, view: View) {
            if (getItemViewType(position) == 2) {
                initializePlayer()
                playerControlView =
                    view.findViewById<PlayerControlView>(R.id.playerControlView)?.apply {
                        player = exoPlayer
                        show()
                    }

                attachSurfaceListener(view.findViewById(R.id.svVideo))

            } else {
                view.findViewById<TextView>(R.id.tvBanner)?.run {
                    setBackgroundColor(list[position].color)
                    text = "$position"
                }
            }
        }
    }

    override fun onDestroy() {

        with(bannerAdapter2.viewHolder) {
            surfaceControl?.release()
            surfaceControl = null
            videoSurface?.release()
            videoSurface = null
            exoPlayer?.release()
            exoPlayer = null
        }
        super.onDestroy()
    }
}


