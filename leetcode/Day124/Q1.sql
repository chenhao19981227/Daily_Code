select 'Low Salary' as category, sum(if(income<20000,1,0)) as accounts_count
from Accounts
union
select 'Average Salary' as category, sum(if(income<=50000 and income>=20000,1,0)) as accounts_count
from Accounts
union
select 'High Salary' as category, sum(if(income>50000,1,0)) as accounts_count
from Accounts