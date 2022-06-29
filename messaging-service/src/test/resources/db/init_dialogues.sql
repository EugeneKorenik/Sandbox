/*
        first_account_id   uuid := '2fa4a470-b2b2-11ec-b909-0242ac120002';
        second_account_id  uuid := '2fa4a862-b2b2-11ec-b909-0242ac120002';
        third_account_id   uuid := '2fa4ab1e-b2b2-11ec-b909-0242ac120002';
        fourth_account_id  uuid := '2fa4ad9e-b2b2-11ec-b909-0242ac120002';
        fifth_account_id   uuid := '2fa4b3a2-b2b2-11ec-b909-0242ac120002';

        first_dialogue_id  uuid := '2fa4b578-b2b2-11ec-b909-0242ac120002';
        second_dialogue_id uuid := '2fa4b7f8-b2b2-11ec-b909-0242ac120002';
        group_dialogue_id  uuid := '2fa4ba5a-b2b2-11ec-b909-0242ac120002';

        first_message_id uuid := '9fcea1d3-03ac-4c68-86e4-46b48777256a';
        second_message_id uuid := '44e916e2-8662-4f2e-933c-2d50fdf8a17e';
        third_message_id uuid := '82923581-381e-464c-92b9-8ace5bd592ce';
*/

insert into dialogue(id, name)
values ('2fa4b578-b2b2-11ec-b909-0242ac120002', 'first_dialogue');
insert into dialogue(id, name)
values ('2fa4b7f8-b2b2-11ec-b909-0242ac120002', 'second_dialogue');
insert into dialogue(id, name)
values ('2fa4ba5a-b2b2-11ec-b909-0242ac120002', 'group_dialogue');

/** First and second user */
insert into dialogue_watcher(target_id, watcher_id)
values ('2fa4b578-b2b2-11ec-b909-0242ac120002', '2fa4a470-b2b2-11ec-b909-0242ac120002');
insert into dialogue_watcher(target_id, watcher_id)
values ('2fa4b578-b2b2-11ec-b909-0242ac120002', '2fa4a862-b2b2-11ec-b909-0242ac120002');

/** Third and fourth user */
insert into dialogue_watcher(target_id, watcher_id)
values ('2fa4b7f8-b2b2-11ec-b909-0242ac120002', '2fa4ab1e-b2b2-11ec-b909-0242ac120002');
insert into dialogue_watcher(target_id, watcher_id)
values ('2fa4b7f8-b2b2-11ec-b909-0242ac120002', '2fa4ad9e-b2b2-11ec-b909-0242ac120002');

/** First, second and third user */
insert into dialogue_watcher(target_id, watcher_id)
values ('2fa4ba5a-b2b2-11ec-b909-0242ac120002', '2fa4a470-b2b2-11ec-b909-0242ac120002');
insert into dialogue_watcher(target_id, watcher_id)
values ('2fa4ba5a-b2b2-11ec-b909-0242ac120002', '2fa4a862-b2b2-11ec-b909-0242ac120002');
insert into dialogue_watcher(target_id, watcher_id)
values ('2fa4ba5a-b2b2-11ec-b909-0242ac120002', '2fa4ab1e-b2b2-11ec-b909-0242ac120002');