select product_name,sum(unit) unit
from orders a
         left join products b
                   on a.product_id = b.product_id
where order_date like '2020-02%'
group by 1
having sum(unit) >= 100
