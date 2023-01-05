package com.example.unsplashattestation.data.api

const val ACCESS_KEY = "mj_2vu5NRd4iwh6pYzpqOymkmK79_WqclNutm5-O2UQ"
const val SECRET_KEY = "C_vgTr-b2b6-ZdhllFr35MO5uNLk5mxGmFpuoq4-Bt0"
const val REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob"
const val SCOPE =
    "public+read_user+" +
            "write_user+read_photos+" +
            "write_photos+" +
            "write_likes+" +
            "write_followers+" +
            "read_collections+" +
            "write_collections"

const val CALL =
    "https://unsplash.com/oauth/authorize" +
            "?client_id=" + ACCESS_KEY +
            "&redirect_uri=" + REDIRECT_URI +
            "&response_type=code" +
            "&scope=" + SCOPE


const val TOKEN_SHARED_NAME = "pref_token"
const val TOKEN_SHARED_KEY = "token"
const val TOKEN_ENABLED_KEY = "token_enabled"
const val ONBOARDING_IS_SHOWN = "onboarding_is_shown"

//раз
//const val ACCESS_KEY = "OOlbm6FJx2pFPfaU8uLWLbsQ1Ez0u1J-tXX5L8WLya8"
//const val SECRET_KEY = "70SAVhXIiZ6ySujad4zR7b4-PZ9TymGOU_H_qDu-9_A"

//два
//const val ACCESS_KEY = "TQBtxa7iGDE6aUYk12mA1mWdM7qLwJrkKgIsNXYEbtY"
//const val SECRET_KEY = "rA8Ntvr_vPSHofWpYz4Ib4coselWLvv8hcwjTv8lOA0"

//три
//const val ACCESS_KEY = "aFYYZK6kx8GMEoIHFhzT27rhwgZZtnENvc0M8mAW7XI"
//const val SECRET_KEY = "cOKBHRzF_cc6Cer9SKQ4gR_Y4vD-YFE2u-YGbLYqEMA"

//четыре
//const val ACCESS_KEY = "mj_2vu5NRd4iwh6pYzpqOymkmK79_WqclNutm5-O2UQ"
//const val SECRET_KEY = "C_vgTr-b2b6-ZdhllFr35MO5uNLk5mxGmFpuoq4-Bt0"
