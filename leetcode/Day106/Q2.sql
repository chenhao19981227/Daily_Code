select project_id,round(sum(experience_years)/count(project_id),2) as average_years
from Project p left join Employee e
                         on p.employee_id=e.employee_id
where experience_years is not null
group by project_id
order by project_id