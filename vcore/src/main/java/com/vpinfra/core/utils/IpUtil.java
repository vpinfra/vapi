package com.vpinfra.core.utils;


import com.vpinfra.core.common.Constant;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * ip 相关工具类
 *
 * @author 尹俊峰
 * @date 2017年3月10日
 * @since 2.1.1
 */
public final class IpUtil {

    private IpUtil() {
    }

    /**
     * 获取服务器内网ip地址.
     *
     * @param prefix ip地址网段前缀,比如：192. , 10., 120.等
     * @return
     * @throws Exception
     */
    public static String getServerInnerIp(String prefix) throws SocketException {
        String innerIp = null;
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            Enumeration<InetAddress> addresss = ni.getInetAddresses();
            while (addresss.hasMoreElements()) {
                InetAddress nextElement = addresss.nextElement();
                String hostAddress = nextElement.getHostAddress();
                if (hostAddress.startsWith(prefix)) {
                    innerIp = hostAddress;
                }
            }
        }

        return innerIp;
    }

    /**
     * 将127.0.0.1形式的 IP 地址转换成十进制整数.
     *
     * @param strIp String形式的 ip 地址，例如：192.168.1.XXX
     * @return ip longip 地址
     */
    public static long ipToLong(String strIp) {
        long[] ip = new long[4];

        //先找到IP地址字符串中.的位置
        int position1 = strIp.indexOf(Constant.DOT);
        int position2 = strIp.indexOf(Constant.DOT, position1 + 1);
        int position3 = strIp.indexOf(Constant.DOT, position2 + 1);

        //将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3 + 1));

        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    /**
     * 将十进制整数形式转换成127.0.0.1形式的ip地址.
     *
     * @param longIp long类型ip地址
     * @return 字符串形式的ip
     */
    public static String longToIP(long longIp) {
        StringBuilder sb = new StringBuilder("");
        //直接右移24位
        sb.append(String.valueOf(longIp >>> 24));
        sb.append(Constant.DOT);
        //将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(Constant.DOT);
        //将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        //将高24位置0
        sb.append(String.valueOf(longIp & 0x000000FF));
        return sb.toString();
    }

}
