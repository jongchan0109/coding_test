SELECT
    P.PRICE DIV 10000 * 10000 AS PRICE_GROUP,
        count(P.PRODUCT_ID) AS PRODUCTS
FROM PRODUCT P
GROUP BY P.PRICE DIV 10000
ORDER BY P.PRICE DIV 10000 ASC