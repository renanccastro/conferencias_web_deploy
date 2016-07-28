package br.com.tecsinapse.util;

import com.google.common.base.Splitter;

/**
 * Created by filipenevola on 07/06/14.
 */
public interface Constantes {
    String TMP_DIR = "/tmp";
    String REST_USER = "rest@tecsinapse.com.br";
    String SENHA_BASIC = "dc70cbc6a54bb92cc4c6c17989a4c78eaea6be6ea3bc95710af9458047c108fe";
    String LIP_MAIL = "lip@tecsinapse.com.br";
    String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";

    String DEFAULT_PROFILE_IMAGE = "iVBORw0KGgoAAAANSUhE" +
            "UgAAAPcAAAD3BAMAAAAzot9gAAAAD1BMVEWXmZaqrKm8vrvP0c7l5" +
            "+SJiHyqAAAECUlEQVR42u3c0ZHaShAF0KshAa2dAC+DfRn4w2EbNgEWK" +
            "QCEJoAVOwlo2n5f2IYnoe6eaSjm/lFQnL3dI5YqquS+2+Wbg2EKXvCCF7zg" +
            "d4bT6fgrnyE/TsfdZv8f/r7b9Hlx6jbHcH7wo8+In97/0rqNz4V3+4s906HJgx+" +
            "uDnl4y4DTweNqxm16vPvf7ca31Pi592XGJi0+eO6zcjzOdDuEdDjtMZN9OnyY" +
            "LRZbPi5/54/Ax+UzbdLg402lRs/H5Z2OfFy+zej1cWoBZnU5HgGoV3f8jcurO" +
            "62jfk4MyniPBWl0cfJL8FEXH7AorSreLsNPmnjEssSgiJ+wMK0i3i7Fox4egT" +
            "RzdymmDng13C/HT1o4heV41MIjGPFK+Kcl3nLwqIMTDPERrHgVPFjinoeTZfO" +
            "ogUfwQpY4vAIeHhKngvNxgiEewU6QN7fEYYmHB8XpaXGIcRg2JyNcHsvmMG0e" +
            "pM3Lr8ichPLjfTlwC/GqXGoPh9dl5xbN62dtXklxmOK1QLfEq6fFazFePS3uL" +
            "HD50isY4k7hJWvL5hV/5XLccfH1YzaXL93BEK9U8MoSd4LzJm9eG+CypTulF7" +
            "0Y4LKl13xcvvQ1G5cvvaq18C/ilet/CsoPqUOSua8N8arWw1dL8RX08GrNnro" +
            "chym+Ykydh8vn/sLH5XNfs3H53F3NxuVzd9ov/GcB/qqNrxJMHU69Dr5AHf/K" +
            "OOsMXHjkXM3G5UfOIQHu+FOX49U33JIVH5dfbf8iCV69so+bHMdX7W+6DrrVX" +
            "9m4vNYKbFy+0Bc+Lh/qOiFeYSa1Ie5giFePiwt3WhfcAkdSHJbNq/ttXsGkuR" +
            "yXpzS3wculVnZecBWcpp8teBr8cxoPSfGZd0+Le0ymT4mPmE6U4bJm5AW4+L3" +
            "7dPgwv5eQCqfW5GY25+Lz1e3uIQTsDe8hhNjwcfmtdAavjw8tbkwX2Lh8mvQe" +
            "dPFhC/B0Of7RYElo59Vw2rVYmEOjg1O3CVicYdMr4CfmTSap2wYhToc9uIm7X" +
            "oTHjYcg3VaAD1vIEjeBi3cNpKGd5+GHHgo5+Amc8TerXfJu7iNSnqFZip/th"  +
            "LqT3U5S9i/ezZ4S3VM3j38o2+dJzuNjC/W834jHN+iHtrfhe6RIbCbw+W+p+"  +
            "kfeMRbOTDeLU4NUobc5fAhIljFM47FFwuyn8SNSJrZT+OiRNMMU3iBtqL2Gn"  +
            "09EzuqOX1xe3fGLy6s7RnF59Us8BmTI6Tp+Qo5Efw2nFlnSX8NH5Ml4DW+RKe" +
            "0lTiEXfrrER+RKvMQ9ssXfFR6RL/Gu8ICMCX/hPifu76h5RM7EP3HKipMpH" +
            "v7AA7LmjnBvhFuEfsep4M+A43c85GbDveAoY3+2sVN2lkpz4+awbE6wyU+pN" +
            "6ugoJeLugAAAABJRU5ErkJggg==";

    String DEFAULT_EMAIL_PREFIX = "@tecsinapse.com.br";
    Splitter COMMA_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();
}
