package daiw.com.informationsite.utils.download;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/6  11:56 AM
 *
 *         ***************************
 */
public interface IDownLoadCallback {
    /**
     * 开始前等待
     */
        void pending();

    /**
     * 下载进度回调
     * @param soFarBytes 当前进度
     * @param totalBytes 总大小
     */
    void progress(int soFarBytes, int totalBytes);

    /**
     * 完成下载
     */
    void completed();

    /**
     * 暂停
     * @param soFarBytes
     * @param totalBytes
     */
    void paused(int soFarBytes, int totalBytes);

    /**
     * 下载出错
     * @param e
     */
    void error(Throwable e);

    /**
     * 已存在相同下载
     */
    void warn();
}
