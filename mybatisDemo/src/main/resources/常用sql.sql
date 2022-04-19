1. 通过一个表的字段修改另外一个表
UPDATE live,
    my_account
SET live.`name` = my_account.merchant_name
WHERE
    live.user_id = my_account.id

2. 备份表

create table hy_video_played_record_20220414 select *from hy_video_played_record