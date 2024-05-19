select  visited_on,sum_amount as amount,round(sum_amount/7,2) as average_amount
from(
        select visited_on, sum(amount) over (order by visited_on rows 6 preceding) as sum_amount
        from (
                 select visited_on,sum(amount) as amount
                 from customer
                 group by visited_on
             ) T
    ) TT
where datediff(visited_on,(select min(visited_on) from customer)) >=6
