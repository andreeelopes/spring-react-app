import * as React from "react";

export interface IList<T> {
    title: string;
    list: T[];
    // select: (x: number) => void;
    show: (x: T) => any;
}

const SimpleList = function <T>({title, list, show}: IList<T>) { // tslint:disable-line
    if (!(typeof list === "undefined") && list.length > 0) {
        return (
            <div className={"card-container"}>
                <div className={"blue-card-header"}>
                    <div className={"blue-card-subtitle"}>
                        {title}
                    </div>
                </div>
                <div className={"blue-card-content"}>
                    <ul>
                        {
                            list.map((c, i) => (
                                    <li key={i}>
                                        {show(c)}
                                    </li>
                                )
                            )
                        }
                    </ul>
                </div>
            </div>);
    }
    return null;
};

export default SimpleList;