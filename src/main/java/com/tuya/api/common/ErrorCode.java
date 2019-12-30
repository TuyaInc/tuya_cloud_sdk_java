package com.tuya.api.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 错误码
 */
public class ErrorCode {

    public static final Map<Integer, String> map = new ConcurrentHashMap<Integer, String>(){{
        put(500, "system error,please contact the admin");

        //-------1000-1999为系统层面异常-------//
        put(1000, "data not exist");
        put(1001, "secret invalid");
        put(1002, "access_token is null");
        put(1003, "grant type invalid");
        put(1004, "sign invalid");
        put(1005, "clientId invalid");
        put(1006, "not support content type");
        put(1007, "not support key");
        put(1008, "invalid access");
        put(1010, "token is expired");
        put(1010, "token invalid");
        put(1012, "token status is invalid");
        put(1013, "request time is invalid");
        put(1100, "param is empty");
        put(1101, "params range invalid");
        put(1102, "param is null");
        put(1103, "commands issue error");
        put(1104, "type is incorrect");
        put(1105, "missing the header");
        put(1106, "permission deny");
        put(1107, "code invalid");
        put(1108, "uri path invalid");
        put(1109, "param is illegal, please check it");
        put(1110, "concurrent request over limit");


        // 2001-2200为业务异常
        put(2001, "device is offline");
        put(2002, "this user dosen`t have any devices");
        put(2003, "function not support");
        put(2004, "not support the lock type");
        put(2005, "product not exist");
        put(2006, "user not exist");
        put(2007, "device token expired");
        put(2008, "command or value not support");
        put(2009, "not support this device");
        put(2010, "device not exist");
        put(2012, "application not support");
        put(2013, "add timer failed");
        put(2014, "this device dosen`t have any timers");
        put(2015, "this category is not supported");
        put(2016, "remote control is removed or does not exist");
        put(2017, "schema does not exist");
        put(2018, "data decrypt failed");
        put(2019, "time over two hours");
        put(2020, "Only third-party clouds are supported");
        put(2021, "Illegal email");
        put(2022, "Illegal phone");
        put(2023, "user exist");
        put(2024, "device file path is not standardized");
        put(2025, "device and file path mismatch");
        put(2026,"ip failed to get");
        put(2027,"size too large");


        // 2201-2300 为定时异常
        put(2201, "time overlap");
        put(2202, "date format error");
        put(2203, "A maximum of 15 timer tasks can be added ");
        put(2204, "A maximum of 30 timer tasks can be added ");
        put(2205, "Overlaps with timer task, please select again ");
        put(2206, "not support timer type");
        put(2207, "not support timer type");
        put(2208, "timer delete error");
        put(2209, "The scheduled task has expired ");
        put(2210, "");
        put(2211, "the device group is not exit");
        put(2212, "the device group no device was selected");


        // 2301-2400门锁异常
        put(2301, "the password is not confirm");
        put(2302, "password encryption error");
        put(2303, "The number of passwords has reached the limit！");
        put(2304, "password has expired!");
        put(2305, "The number of created passwords has reached the limit！ ");
        put(2306, "Illegal operation！");
        put(2307, "The maximum number of available 99！");
        put(2308, "The maximum number of password operations");
        put(2309, "Mobile phone number does not conform to the rules");
        put(2310, "zigbee lock password not found");
        put(2311, "there are too many passwords in setting, please wait for gateway processing!");
        put(2312, "Door lock equipment is not online!");
        put(2313, "Initializing information, please be patient. If you haven't succeeded for a long time, please redistribute your network. ");
        put(2314, "password exception");
        put(2315, "no effective password");
        put(2316, "password name can not repeat!");
        put(2317, "Gateway's other sub device is updating, please try later!");
        put(2318, "the effective time or invalid time is less than the current time");
        put(2319,"not sufficient SMS balance, please contact the manufacturer to recharge, cancel the phone number, you can create it! ");
        put(2320,"The SMS balance of the manufacturer is insufficient. There is no SMS notification for this operation! ");
        put(2321, "wifi lock password not found");
        put(2322, "wifi lock password can not be repeated");
        put(2323,"door lock password record does not exist");
        put(2324,"door lock password status does not allow operation");

        // 2401-2500 为登录页异常
        put(2401, "username or password wrong");
        put(2402, "country code invalid");
        put(2403, "country code not support this area");
        put(2405, "ticket invalid");
        put(2406, "skill id invalid");
        put(2407, "no trace verification failed");
        put(2408, "google token invalid");
        put(2409, "google token timeout or duplicate");
        put(2410, "region not support");
        put(2411, "need secondary validation");
        put(2412, "verification code error");
        put(2413, "excessive text messaging");
        put(2414, "verification code is already in use");
        put(2415, "verification code has expired");
        put(2416, "verification code does not exist");
        put(2417, "SMS is sent too fast, try again in one minute");
        put(2421, "failed to send sms");
        put(2418, "only support pre-issue and online");
        put(2419,"only support mobile phone number and email to send authentication code");
        put(2420, "verification codes that do not support this approach");

        // 2501-2600 为全屋场景异常
        put(2501, "home not exist");
        put(2502, "room not exist");
        put(2503, "scene not exist");
        put(2504, "user in different country");
        put(2505, "user relation is error");
        put(2506, "the member is not register");
        put(2507, "the member is in family");
        put(2508, "the member must is admin in family");
        put(2509, "the member is not exist");
        put(2510, "the member name is exist");
        put(2511, "the real devices are not match the scene template");
        put(2512, "action is empty");
        put(2513, "scene and home do not match");
        put(2514, "scene and device are not related");
    }};
}
