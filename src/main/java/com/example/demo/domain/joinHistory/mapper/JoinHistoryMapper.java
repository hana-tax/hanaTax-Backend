package com.example.demo.domain.joinHistory.mapper;

import com.example.demo.domain.joinHistory.dto.JoinHistoryDto;
import com.example.demo.domain.joinHistory.dto.PeerAssetsDTO;
import com.example.demo.domain.joinHistory.dto.TaxAssetTopDTO;
import com.example.demo.domain.joinHistory.dto.UserAssetsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JoinHistoryMapper {
    @Select("SELECT * FROM join_history WHERE id = #{id} AND accountType = 3 and joinStatus = 14")
    List<JoinHistoryDto> getSavingAccountsById(String id);

    @Select("SELECT * FROM join_history WHERE id = #{id} AND accountType = 4 and joinStatus = 14")
    List<JoinHistoryDto> getIsaAccountsById(String id);

    @Select("SELECT * FROM join_history WHERE id = #{id} AND accountType = 5 and joinStatus = 14")
    List<JoinHistoryDto> getFundAccountsById(String id);

    @Select("""
            SELECT 
                (SELECT NVL(SUM(balance), 0) FROM deposit_account WHERE id = #{id} AND accountStatus = 6) +
                (SELECT NVL(SUM(balance), 0) FROM fund_account WHERE id = #{id} AND accountStatus = 6) +
                (SELECT NVL(SUM(balance), 0) FROM isa_account WHERE id = #{id} AND accountStatus = 6) AS total_balance
            FROM dual
            """)
    Double getTotalBalance(@Param("id") String id);
    @Select("""
    SELECT 
        FLOOR(AVG(CASE WHEN jh.ACCOUNTTYPE = 3 THEN jh.JOINACCOUNT ELSE 0 END)) AS safeAssets,
        FLOOR(AVG(CASE WHEN jh.ACCOUNTTYPE = 68 OR jh.ACCOUNTTYPE = 69 OR jh.ACCOUNTTYPE = 70 THEN jh.JOINACCOUNT ELSE 0 END)) AS investmentAssets,
        FLOOR(AVG(CASE WHEN jh.ACCOUNTTYPE = 71 OR jh.ACCOUNTTYPE = 72 THEN jh.JOINACCOUNT ELSE 0 END)) AS pensionAssets,
        FLOOR(AVG(CASE WHEN jh.ACCOUNTTYPE = 73 OR jh.ACCOUNTTYPE = 74 THEN jh.JOINACCOUNT ELSE 0 END)) AS foreignAssets,
        FLOOR(AVG(CASE WHEN jh.ACCOUNTTYPE = 4 OR jh.ACCOUNTTYPE = 76 OR jh.ACCOUNTTYPE = 5 THEN jh.JOINACCOUNT ELSE 0 END)) AS taxSavingAssets
    FROM join_history jh
    JOIN users u ON jh.ID = u.ID
    WHERE 
        CASE 
            WHEN SUBSTR(u.RESIDENTNUMBER, 1, 2) <= '21' THEN 2000 + TO_NUMBER(SUBSTR(u.RESIDENTNUMBER, 1, 2))
            ELSE 1900 + TO_NUMBER(SUBSTR(u.RESIDENTNUMBER, 1, 2))
        END 
        BETWEEN #{startYear} AND #{endYear}
""")
    PeerAssetsDTO getPeerAssetsByAgeGroup(int startYear, int endYear);

    @Select("""
    SELECT 
        SUM(CASE WHEN jh.ACCOUNTTYPE = 3 THEN jh.JOINACCOUNT ELSE NULL END) AS safeAssets,
        SUM(CASE WHEN jh.ACCOUNTTYPE = 68 OR jh.ACCOUNTTYPE = 69 OR jh.ACCOUNTTYPE = 70 THEN jh.JOINACCOUNT ELSE NULL END) AS investmentAssets,
        SUM(CASE WHEN jh.ACCOUNTTYPE = 71 OR jh.ACCOUNTTYPE = 72 THEN jh.JOINACCOUNT ELSE NULL END) AS pensionAssets,
        SUM(CASE WHEN jh.ACCOUNTTYPE = 73 OR jh.ACCOUNTTYPE = 74 THEN jh.JOINACCOUNT ELSE NULL END) AS foreignAssets,
        SUM(CASE WHEN jh.ACCOUNTTYPE = 4 OR jh.ACCOUNTTYPE = 76 OR jh.ACCOUNTTYPE = 5 THEN jh.JOINACCOUNT ELSE NULL END) AS taxSavingAssets
    FROM join_history jh
    WHERE jh.ID = #{userId}
""")
    UserAssetsDTO getUserAssetsById(String userId);

    @Select("""
        SELECT 
            ACCOUNTTYPE, 
            SUM(jh.JOINACCOUNT) AS totalAmount
        FROM join_history jh
        JOIN users u ON jh.ID = u.ID
        WHERE 
            (CASE 
                WHEN TO_NUMBER(SUBSTR(u.RESIDENTNUMBER, 1, 2)) <= 21 THEN 2000 + TO_NUMBER(SUBSTR(u.RESIDENTNUMBER, 1, 2))
                ELSE 1900 + TO_NUMBER(SUBSTR(u.RESIDENTNUMBER, 1, 2))
            END) BETWEEN #{startYear} AND #{endYear}
            AND (jh.ACCOUNTTYPE = 4 OR jh.ACCOUNTTYPE = 76 OR jh.ACCOUNTTYPE = 5)
        GROUP BY jh.ACCOUNTTYPE
        ORDER BY totalAmount DESC
        FETCH FIRST 3 ROWS ONLY
    """)
    List<TaxAssetTopDTO> getTopTaxSavingAssetsByAgeGroup(@Param("startYear") int startYear, @Param("endYear") int endYear);
}
