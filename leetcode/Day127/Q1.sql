SELECT (
           CASE
               WHEN id%2=0 THEN id-1
               WHEN id%2=1 AND id<(SELECT max(id) FROM Seat) THEN id+1
        ELSE id
    END
           ) id, student FROM Seat
ORDER BY id