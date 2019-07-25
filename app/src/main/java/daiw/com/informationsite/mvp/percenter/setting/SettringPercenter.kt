package daiw.com.informationsite.mvp.percenter.setting

import daiw.com.informationsite.base.BasePercenter
import daiw.com.informationsite.mvp.contract.setting.ISettingContract
import daiw.com.informationsite.mvp.model.SettingModel

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2019-07-26  00:43
 *
 *         ***************************
 */
class SettringPercenter(view: ISettingContract.ISettingView)
    : BasePercenter<ISettingContract.ISettingView,ISettingContract.ISettingModel>(view,SettingModel()) {

}