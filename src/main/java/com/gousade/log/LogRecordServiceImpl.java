package com.gousade.log;

import com.google.common.collect.Lists;
import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LogRecordServiceImpl implements ILogRecordService {
    @Override
    public void record(LogRecord logRecord) {
        log.info("[logRecord]log={}", logRecord);
    }

    @Override
    public void batchRecord(List<LogRecord> records) {
        Optional.ofNullable(records).ifPresent(x -> x.forEach(y ->
                log.info("[logRecord]log={}", y)));
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return Lists.newArrayList();
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return Lists.newArrayList();
    }
}
