#ifndef MAINWIDGET_H
#define MAINWIDGET_H

#include <QWidget>
#include <QThread>
//#include <QSemaphore>
#include <QDebug>

class ZcsThread : public QThread
{
    Q_OBJECT
public:
    ZcsThread() {}

    ~ZcsThread() {}

    void run()
    {
        while(true)
        {
            QThread::usleep(1000000);
            qDebug() << 123;
        }
    }

signals:
    void signal_update();
};

class MainWidget : public QWidget
{
    Q_OBJECT

public:
    MainWidget(QWidget *parent = nullptr);
    ~MainWidget();

private:
    ZcsThread *zthread_;
};
#endif // MAINWIDGET_H
