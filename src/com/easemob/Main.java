package com.easemob;


import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.UUID;

import static com.easemob.ConversionUtils.bytebuffer;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String tip = "请输入数字进行选择: 1(16进制转uuid),2(uuid转16进制),3(999:退出)";
        System.out.println(tip);

        while (in.hasNext()) {
            try {
                int type = in.nextInt();
                switch (type) {
                    case 1:
                        System.out.println("请输入16进制数字");
                        if (in.hasNext()) {
                            in.nextLine();
                            String line = in.nextLine();

                            String s = hexToUuid(line.substring(2));
                            System.out.println(s);
                            break;
                        }
                    case 2:
                        System.out.println("请输入uuid");
                        if (in.hasNext()) {
                            in.nextLine();
                            String uuidline = in.nextLine();

                            UUID uuid = UUID.fromString(uuidline);
                            ByteBuffer tokenUUID = bytebuffer(uuid);
                            String hex = bytesToHex(tokenUUID.array());
                            System.out.println(hex);
                        }
                    case 999:
                        break;
                }
                if (type == 999) break;
            } catch (Exception e) {
                System.err.println("无效输入，请重试");
                in.nextLine();
            }

            System.out.println(tip);
        }
        System.out.println("正在退出...");
        System.exit(0);

//        UUID uuid = UUID.randomUUID();
//
//        String s = uuid.toString();
//
//        System.out.println(s);
//        byte[] bytes = s.getBytes();
//        System.out.println(bytes);

//        UUID uuid1 = UUID.fromString("1bf57320-dc04-11e7-bfb7-77fff0ef37ea");
//        System.out.println(uuid1);
//        ByteBuffer tokenUUID = bytebuffer(uuid1);
//
//        String s = bytesToHex(tokenUUID.array());
//        System.out.println(s);
//
//        String s1 = "04573a00d37111e7b15d95926849f19230343537336130302d643337312d313165372d623135642d3935393236383439663139323a726f6c65733a696e6465786573";
//        byte[] b = new BigInteger(s1, 16).toByteArray();
//
//        byte[] bytes = copy(b, 0, 16);
//        byte[] bytes1 = copy(b, 16, b.length - 16);
//        UUID uuid = ConversionUtils.uuid(bytes);
//        System.out.println(uuid);

//
//        String string = ConversionUtils.string(b);
//        System.out.println(string);
//
//        String string = ConversionUtils.string(b, 1, b.length - 1);
//        String[] split = string.split(":r");
//        UUID uuid = UUID.fromString(split[0]);


        //System.out.println(uuid);
        //UUID uuid = ConversionUtils.uuid(b);
        //System.out.println(uuid);

//        ByteBuffer byteBuffer = ConversionUtils.bytebuffer(CassandraPersistenceUtils.key(UUID.fromString("04573a00-d371-11e7-b15d-95926849f192"), "credentials"));
//        String s = bytesToHex(byteBuffer.array());
//        System.out.println(s);
    }

    public static String hexToUuid(String hex) {
        byte[] array = new BigInteger(hex, 16).toByteArray();
        UUID uuid = ConversionUtils.uuid(array);
        return uuid.toString();
    }

    public static final byte[] copy(byte[] data, int pos, int length) {
        byte[] transplant = new byte[length];

        System.arraycopy(data, pos, transplant, 0, length);

        return transplant;
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
