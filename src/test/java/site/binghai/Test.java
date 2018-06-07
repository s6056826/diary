package site.binghai;

import com.alibaba.fastjson.JSONObject;
import site.binghai.utils.IoUtils;

import java.util.HashMap;

/**
 * Created by IceSea on 2018/5/6.
 * GitHub: https://github.com/IceSeaOnly
 */
public class Test {

    @org.junit.Test
    public void name() throws Exception {
        String[] addrs = {"n1FF1nz6tarkDVwWQkMnnwFPuPKUaQTdptE",
                "n1GmkKH6nBMw4rrjt16RrJ9WcgvKUtAZP1s",
                "n1H4MYms9F55ehcvygwWE71J8tJC4CRr2so",
                "n1JAy4X6KKLCNiTd7MWMRsVBjgdVq5WCCpf",
                "n1LkDi2gGMqPrjYcczUiweyP4RxTB6Go1qS",
                "n1LmP9K8pFF33fgdgHZonFEMsqZinJ4EUqk",
                "n1MNXBKm6uJ5d76nJTdRvkPNVq85n6CnXAi",
                "n1NrMKTYESZRCwPFDLFKiKREzZKaN1nhQvz",
                "n1NwoSCDFwFL2981k6j9DPooigW33hjAgTa",
                "n1PfACnkcfJoNm1Pbuz55pQCwueW1BYs83m",
                "n1Q8mxXp4PtHaXtebhY12BnHEwu4mryEkXH",
                "n1RYagU8n3JSuV4R7q4Qs5gQJ3pEmrZd6cJ",
                "n1SAQy3ix1pZj8MPzNeVqpAmu1nCVqb5w8c",
                "n1SHufJdxt2vRWGKAxwPETYfEq3MCQXnEXE",
                "n1SSda41zGr9FKF5DJNE2ryY1ToNrndMauN",
                "n1TmQtaCn3PNpk4f4ycwrBxCZFSVKvwBtzc",
                "n1UM7z6MqnGyKEPvUpwrfxZpM1eB7UpzmLJ",
                "n1UnCsJZjQiKyQiPBr7qG27exqCLuWUf1d7",
                "n1XkoVVjswb5Gek3rRufqjKNpwrDdsnQ7Hq",
                "n1cYKNHTeVW9v1NQRWuhZZn9ETbqAYozckh",
                "n1dYu2BXgV3xgUh8LhZu8QDDNr15tz4hVDv"};

        int port = 8784;

        String ori = "network {\n" +
                "  seed: [\"/ip4/127.0.0.1/tcp/8680/ipfs/QmP7HDFcYmJL12Ez4ZNVCKjKedfE7f48f1LAkUc3Whz4jP\"]\n" +
                "  listen: [\"0.0.0.0:%d\"]\n" +
                "  network_id: 1\n" +
                "}\n" +
                "\n" +
                "chain {\n" +
                "  chain_id: 100\n" +
                "  datadir: \"miner.%d.db\"\n" +
                "  keydir: \"keydir\"\n" +
                "  genesis: \"conf/default/genesis.conf\"\n" +
                "  \n" +
                "  start_mine: true\n" +
                "  coinbase: \"n1HtG7ViuWAhMb4X9cuRWCvNRMCyeNRxUzG\"\n" +
                "  miner: \"%s\"\n" +
                "  passphrase: \"passphrase\"\n" +
                "\n" +
                "  signature_ciphers: [\"ECC_SECP256K1\"]\n" +
                "}\n" +
                "\n" +
                "rpc {\n" +
                "    rpc_listen: [\"127.0.0.1:%d\"]\n" +
                "    http_listen: [\"127.0.0.1:%d\"]\n" +
                "    http_module: [\"api\",\"admin\"]\n" +
                "    \n" +
                "    # http_cors: []\n" +
                "}\n" +
                "\n" +
                "app {\n" +
                "    log_level: \"debug\"\n" +
                "    log_file: \"logs/miner.%d\"\n" +
                "    enable_crash_report: true\n" +
                "}\n" +
                "\n" +
                "stats {\n" +
                "    enable_metrics: false\n" +
                "    influxdb: {\n" +
                "        host: \"http://localhost:8086\"\n" +
                "        db: \"nebulas\"\n" +
                "        user: \"admin\"\n" +
                "        password: \"admin\"\n" +
                "    }\n" +
                "}";

        for (int i = 1; i <= 21; i++) {
            IoUtils.WriteCH("miner_"+i+".conf",String.format(ori,port++,i,addrs[i-1],port++,port++,i));
        }
    }
    //@org.junit.Test
    public void test2(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",1);
        jsonObject.put("age",2);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id",3);
        stringObjectHashMap.put("name","dnw");
        jsonObject.putAll(stringObjectHashMap);
        System.out.println(jsonObject.toJSONString());

    }
}
