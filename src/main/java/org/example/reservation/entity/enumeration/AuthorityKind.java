package org.example.reservation.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum AuthorityKind {
    ROLE_ADMIN("0", "管理者"),
    ROLE_USER("1", "ユーザー");

    /** コード値 */
    private final String code;

    /** 画面表示する値 */
    private final String displayValue;

    /**
     * コード値に対応する権限種別を取得(0-2)
     *
     * @param code 権限種別のコード値(0 or 1)
     * @return enum 権限種別名
     */
    public static AuthorityKind from(String code){
        return Arrays.stream(AuthorityKind.values())
                .filter(authorityKind -> authorityKind.getCode().equals(code))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid code: " + code));
    }
}