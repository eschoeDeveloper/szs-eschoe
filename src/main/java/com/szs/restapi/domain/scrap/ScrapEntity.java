package com.szs.restapi.domain.scrap;

import com.szs.restapi.globals.component.CryptoFieldConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "SZS_SCRAPPING")
@Getter
@Setter
@ToString
public class ScrapEntity {

    @Column(name = "이름")
    private String 이름;

    @Id
    @Column(name = "주민등록번호")
    @Convert(converter = CryptoFieldConverter.class)
    private String 주민등록번호;

    @Column(name = "총지급액")
    private String 총지급액;

    @Column(name = "산출세액")
    private String 산출세액;

    @Column(name = "보험료")
    private String 보험료;

    @Column(name = "교육비")
    private String 교육비;

    @Column(name = "기부금")
    private String 기부금;

    @Column(name = "의료비")
    private String 의료비;

    @Column(name = "퇴직연금")
    private String 퇴직연금;

}
