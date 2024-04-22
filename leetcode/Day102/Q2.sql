select s.user_id,if(action='null',0,round(sum(if(action='confirmed' ,1,0))/count(s.user_id),2)) as confirmation_rate
from Signups s left join Confirmations c
                         on s.user_id=c.user_id
group by s.user_id
