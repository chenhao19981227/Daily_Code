select ids as id,cnt as num
from (
         select ids,count(*) as cnt
         from (
                  select requester_id ids from RequestAccepted
                  union all
                  select accepter_id from RequestAccepted
              ) t1
         group by ids
     ) t2
order by cnt desc
    limit 1