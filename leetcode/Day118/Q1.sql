select e1.employee_id,e1.name,
       count(e2.employee_id) as reports_count,
       round(sum(e2.age)/count(e2.employee_id),0) as average_age
from Employees e1 join Employees e2
                       on e1.employee_id=e2.reports_to
group by e1.employee_id
order by e1.employee_id