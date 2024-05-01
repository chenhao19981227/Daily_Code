select round(count(distinct player_id)/(select count(distinct player_id) from Activity),2) as fraction
from Activity
where (player_id ,event_date) in(
    select player_id,min(event_date)+interval 1 day
from Activity
group by player_id
    )