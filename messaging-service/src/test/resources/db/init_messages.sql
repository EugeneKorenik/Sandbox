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

insert into message(id, text, dialogue_id, sender_account_id)
values ('9fcea1d3-03ac-4c68-86e4-46b48777256a',
        'Hi, how are things?',
        '2fa4ba5a-b2b2-11ec-b909-0242ac120002',
        '2fa4a470-b2b2-11ec-b909-0242ac120002');

insert into message(id, text, dialogue_id, sender_account_id)
values ('44e916e2-8662-4f2e-933c-2d50fdf8a17e',
        'Hi, nothing to complain, and u?',
        '2fa4ba5a-b2b2-11ec-b909-0242ac120002',
        '2fa4a862-b2b2-11ec-b909-0242ac120002');

insert into message(id, text, dialogue_id, sender_account_id)
values ('82923581-381e-464c-92b9-8ace5bd592ce',
        'Are you sure? Why are you not at your workplace??',
        '2fa4ba5a-b2b2-11ec-b909-0242ac120002',
        '2fa4ab1e-b2b2-11ec-b909-0242ac120002');

/** Bind first message **/
insert into message_watcher(target_id, watcher_id)
values ('9fcea1d3-03ac-4c68-86e4-46b48777256a', '2fa4a470-b2b2-11ec-b909-0242ac120002');
insert into message_watcher(target_id, watcher_id)
values ('9fcea1d3-03ac-4c68-86e4-46b48777256a', '2fa4a862-b2b2-11ec-b909-0242ac120002');
insert into message_watcher(target_id, watcher_id)
values ('9fcea1d3-03ac-4c68-86e4-46b48777256a', '2fa4ab1e-b2b2-11ec-b909-0242ac120002');

/** Bind second message **/
insert into message_watcher(target_id, watcher_id)
values ('44e916e2-8662-4f2e-933c-2d50fdf8a17e', '2fa4a470-b2b2-11ec-b909-0242ac120002');
insert into message_watcher(target_id, watcher_id)
values ('44e916e2-8662-4f2e-933c-2d50fdf8a17e', '2fa4a862-b2b2-11ec-b909-0242ac120002');
insert into message_watcher(target_id, watcher_id)
values ('44e916e2-8662-4f2e-933c-2d50fdf8a17e', '2fa4ab1e-b2b2-11ec-b909-0242ac120002');

/** Bind third message **/
insert into message_watcher(target_id, watcher_id)
values ('82923581-381e-464c-92b9-8ace5bd592ce', '2fa4a470-b2b2-11ec-b909-0242ac120002');
insert into message_watcher(target_id, watcher_id)
values ('82923581-381e-464c-92b9-8ace5bd592ce', '2fa4a862-b2b2-11ec-b909-0242ac120002');
insert into message_watcher(target_id, watcher_id)
values ('82923581-381e-464c-92b9-8ace5bd592ce', '2fa4ab1e-b2b2-11ec-b909-0242ac120002');