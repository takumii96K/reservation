$(function () {
    $('#datetime').datetimepicker({
        format:'Y-m-d H:i',
        minDate: 0, // 今日以降の日付を選択可能にする
        onChangeDateTime: function (dp, $input)
        {
            //現在の日付を取得する
            let currentDate = new Date();
            let currentDay = currentDate.getDate();

            //選択された日付情報を取得
            let selectDate = new Date($input.val());
            let selectDay = selectDate.getDate();

            //選択した日付(selectDay)が現在の日付(currentDay)と一致するか比較
            if(selectDay === currentDay)
            {
                //カレンダーを再表示
                $('#datetime').datetimepicker
                ({
                    format:'Y-m-d H:i',
                    minDate: 0,
                    minTime: 0,
                    //時間指定(時間指定するとUIが消されないのでスクロールバグを回避できる)
                    allowTimes: ['00:00','01:00','02:00','03:00','04:00','05:00',
                        '06:00','07:00','08:00','09:00','10:00','11:00',
                        '12:00','13:00','14:00','15:00','16:00','17:00',
                        '18:00','19:00','20:00','21:00','22:00','23:00','24:00']
                })
            }
            else//現在の日付以外だった場合
            {
                //再表示
                $('#datetime').datetimepicker
                ({
                    format:'Y-m-d H:i',
                    minTime: '00:00',//選べる時間を0～24時に設定
                    //時間指定
                    allowTimes: ['00:00','01:00','02:00','03:00','04:00','05:00',
                        '06:00','07:00','08:00','09:00','10:00','11:00',
                        '12:00','13:00','14:00','15:00','16:00','17:00',
                        '18:00','19:00','20:00','21:00','22:00','23:00','24:00']
                })
            }

        }
    });
});