package daiw.com.informationsite.view.setting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import daiw.com.informationsite.R
import daiw.com.informationsite.base.MvpBaseActivity
import daiw.com.informationsite.mvp.contract.setting.ISettingContract
import daiw.com.informationsite.mvp.percenter.setting.SettringPercenter

/****************************
 * 类描述：设置
 *
 * @author: daiw
 * @time: 2019/07/26  00:36 AM
 *
 * ***************************
 */
class SettringActivity : MvpBaseActivity<SettringPercenter>(R.layout.activity_settring), ISettingContract.ISettingView {
    override fun initView() {
        setTitle(R.string.setting)
    }

    override fun createPercenter(): SettringPercenter {
        return SettringPercenter(this)
    }

    override fun showToast(message: String?) {
    }

    override fun showToast(message: Int) {
    }

    override fun showPragress() {
    }

    override fun dissPragress() {
    }

}
