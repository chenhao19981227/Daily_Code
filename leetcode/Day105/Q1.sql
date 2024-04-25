select p.product_id, round(IFNULL(sum(units*price)/sum(units),0),2) as average_price
from Prices p left join UnitsSold u
                        on u.product_id=p.product_id and u.purchase_date between start_date and end_date
group by p.product_id