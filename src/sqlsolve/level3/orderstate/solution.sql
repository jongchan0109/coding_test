SELECT ORDER_ID,
       PRODUCT_ID,
       DATE_FORMAT(OUT_DATE, '%Y-%m-%d') AS OUT_DATE,
       case
           when OUT_DATE is NULL THEN '출고미정'
           when DATEDIFF('2022-05-01', OUT_DATE) >= 0 THEN '출고완료'
           ELSE '출고대기'
           end AS '출고여부'
FROM FOOD_ORDER
ORDER BY ORDER_ID ASC