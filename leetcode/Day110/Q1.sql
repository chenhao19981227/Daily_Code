SELECT ROUND(SUM(
                     CASE WHEN min_order_date = min_pref_delivery_date THEN 1 ELSE 0 END
             ) / COUNT(DISTINCT customer_id) * 100, 2) AS immediate_percentage
FROM (SELECT customer_id,
             MIN(order_date)                  AS min_order_date,
             MIN(customer_pref_delivery_date) AS min_pref_delivery_date
      FROM Delivery
      GROUP BY customer_id) AS subquery