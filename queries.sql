## Part 1: Test it with SQL
SELECT *
FROM job;

## Part 2: Test it with SQL
SELECT name
FROM employer
WHERE (location="St. Louis City");

## Part 3: Test it with SQL
--Write a SQL statement to remove the job table.
DROP TABLE job;


## Part 4: Test it with SQL
--Write a query to return a list of the names & descriptions of all skills that are attached to jobs in alphabetical order.
--With making use of "is not null", include a condition that won't list a skill if does not have a job listed.'
SELECT * FROM skill
LEFT JOIN job_skills ON skill.id = job_skills.skills_id
INNER JOIN job_skills.skills_id = skill.id
WHERE job_skills.jobs_id IS NOT NULL
ORDER BY name;


