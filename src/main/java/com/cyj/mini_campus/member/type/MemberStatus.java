package com.dokuny.mini_campus.member.type;

public enum MemberStatus {
    WAIT,
    VALID,
    BANNED,
    WITHDRAWAL;

    public static MemberStatus of(String word) {
        if (word.equals("WAIT")) {
            return WAIT;
        } else if (word.equals("VALID")) {
            return VALID;
        } else if (word.equals("BANNED")) {
            return BANNED;
        } else if (word.equals("WITHDRAWAL")) {
            return WITHDRAWAL;
        }
        return null;
    }
}
